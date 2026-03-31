package com.vanthan.supper.trungtam.service;

import com.vanthan.supper.trungtam.config.VNPayConfig;
import com.vanthan.supper.trungtam.entity.Enrollment;
import com.vanthan.supper.trungtam.entity.Payment;
import com.vanthan.supper.trungtam.entity.enums.EnrollmentStatus;
import com.vanthan.supper.trungtam.repository.PaymentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepo paymentRepo;

    @Transactional
    public Payment createPayment(Enrollment enrollment) { //Tạo payment trước khi redirect

        Payment p = new Payment();
        p.setEnrollment(enrollment);
        p.setAmount(enrollment.getCourse().getPrice());
        p.setMethod("VNPAY");
        p.setStatus("PENDING");

        return paymentRepo.save(p);
    }

    //Update khi thanh toán xong
    @Transactional
    public void successPayment(String transactionNo, String studentId, String courseId) {

        Payment p = paymentRepo.findByTransactionNo(transactionNo)
                .orElseThrow();

        p.setStatus("SUCCESS");

        Enrollment e = p.getEnrollment();
        e.setStatus(EnrollmentStatus.PAID);
    }

    public String createVNPayUrl(String courseId, String amount, String transactionId) {
        try {
            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", "2.1.0");
            vnp_Params.put("vnp_Command", "pay");
            vnp_Params.put("vnp_TmnCode", VNPayConfig.vnp_TmnCode);

            // Số tiền nhân 100
            long amountLong = new BigDecimal(amount).multiply(new BigDecimal(100)).longValue();
            vnp_Params.put("vnp_Amount", String.valueOf(amountLong));

            vnp_Params.put("vnp_CurrCode", "VND");
            vnp_Params.put("vnp_TxnRef", transactionId);
            vnp_Params.put("vnp_OrderInfo", "Thanh toan khoa hoc " + courseId);
            vnp_Params.put("vnp_OrderType", "other");
            vnp_Params.put("vnp_Locale", "vn");
            vnp_Params.put("vnp_ReturnUrl", VNPayConfig.vnp_ReturnUrl);
            vnp_Params.put("vnp_IpAddr", "127.0.0.1");

            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            vnp_Params.put("vnp_CreateDate", formatter.format(cld.getTime()));

            // 1. Sắp xếp tham số theo alphabet
            List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
            Collections.sort(fieldNames);

            // 2. Xây dựng chuỗi dữ liệu băm và query
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();

            for (int i = 0; i < fieldNames.size(); i++) {
                String fieldName = fieldNames.get(i);
                String fieldValue = vnp_Params.get(fieldName);

                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    // Encode key và value
                    String encodedKey = URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString());
                    String encodedValue = URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()).replace("+", "%20");

                    // Thêm vào HashData (Dùng để băm)
                    hashData.append(encodedKey);
                    hashData.append('=');
                    hashData.append(encodedValue);

                    // Thêm vào Query (Dùng để tạo URL)
                    query.append(encodedKey);
                    query.append('=');
                    query.append(encodedValue);

                    // Chỉ thêm dấu & nếu KHÔNG PHẢI là tham số cuối cùng có giá trị
                    // (Cách an toàn nhất là kiểm tra xem còn phần tử tiếp theo không)
                    if (i < fieldNames.size() - 1) {
                        // Tìm xem có tham số nào phía sau có giá trị thực không để thêm dấu &
                        boolean hasMore = false;
                        for (int j = i + 1; j < fieldNames.size(); j++) {
                            if (vnp_Params.get(fieldNames.get(j)) != null && !vnp_Params.get(fieldNames.get(j)).isEmpty()) {
                                hasMore = true;
                                break;
                            }
                        }
                        if (hasMore) {
                            query.append('&');
                            hashData.append('&');
                        }
                    }
                }
            }

            // 3. Tạo chữ ký bảo mật
            String secureHash = hmacSHA512(VNPayConfig.vnp_HashSecret, hashData.toString());

            // 4. Kết quả cuối cùng
            String finalUrl = VNPayConfig.vnp_Url + "?" + query.toString() + "&vnp_SecureHash=" + secureHash;

            System.out.println("VNPAY URL: " + finalUrl);
            System.out.println("HASH DATA: " + hashData.toString());

            return finalUrl;

        } catch (Exception e) {
            throw new RuntimeException("Lỗi tạo URL VNPay", e);
        }
    }

    // Hàm băm chuẩn không bao giờ sai
    public static String hmacSHA512(final String key, final String data) {
        try {
            if (key == null || data == null) throw new NullPointerException();
            final Mac hmac512 = Mac.getInstance("HmacSHA512");
            byte[] hmacKeyBytes = key.getBytes(StandardCharsets.UTF_8);
            final SecretKeySpec secretKey = new SecretKeySpec(hmacKeyBytes, "HmacSHA512");
            hmac512.init(secretKey);
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] result = hmac512.doFinal(dataBytes);
            StringBuilder sb = new StringBuilder(2 * result.length);
            for (byte b : result) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (Exception ex) {
            return "";
        }
    }
}
