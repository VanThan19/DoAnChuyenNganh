/* ═══════════════════════════════════════════
   EDULANG CENTER — Main JavaScript
═══════════════════════════════════════════ */

document.addEventListener('DOMContentLoaded', () => {

  /* ─── NAVBAR SCROLL ─── */
  const navbar = document.getElementById('navbar');
  const handleScroll = () => {
    if (window.scrollY > 60) {
      navbar.classList.add('scrolled');
      navbar.classList.remove('transparent');
    } else {
      navbar.classList.remove('scrolled');
      navbar.classList.add('transparent');
    }
    // Scroll to top button
    const btn = document.getElementById('scrollTop');
    if (btn) btn.classList.toggle('visible', window.scrollY > 400);
  };
  window.addEventListener('scroll', handleScroll, { passive: true });
  handleScroll();

  /* ─── MOBILE MENU ─── */
  const hamburger = document.querySelector('.nav-hamburger');
  const navMenu = document.querySelector('.nav-menu');
  const navActions = document.querySelector('.nav-actions');
  if (hamburger) {
    hamburger.addEventListener('click', () => {
      const open = navMenu.style.display === 'flex';
      navMenu.style.cssText = open ? '' : 'display:flex;flex-direction:column;position:absolute;top:72px;left:0;right:0;background:white;padding:16px 24px;box-shadow:0 8px 24px rgba(0,0,0,0.1);gap:4px;';
      navActions.style.cssText = open ? '' : 'display:flex;flex-direction:column;padding:0 24px 16px;background:white;position:absolute;top:calc(72px + ' + (navMenu.offsetHeight||200) + 'px);left:0;right:0;box-shadow:0 8px 24px rgba(0,0,0,0.1);gap:8px;';
    });
  }

  /* ─── SMOOTH SCROLL ─── */
  document.querySelectorAll('a[href^="#"]').forEach(link => {
    link.addEventListener('click', e => {
      const target = document.querySelector(link.getAttribute('href'));
      if (target) {
        e.preventDefault();
        target.scrollIntoView({ behavior: 'smooth', block: 'start' });
      }
    });
  });

  /* ─── SCROLL REVEAL ─── */
  const observer = new IntersectionObserver((entries) => {
    entries.forEach((entry, i) => {
      if (entry.isIntersecting) {
        setTimeout(() => entry.target.classList.add('visible'), i * 80);
        observer.unobserve(entry.target);
      }
    });
  }, { threshold: 0.12, rootMargin: '0px 0px -60px 0px' });

  document.querySelectorAll('.reveal').forEach(el => observer.observe(el));

  /* ─── COUNTER ANIMATION ─── */
  const counters = document.querySelectorAll('[data-count]');
  const counterObserver = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        const el = entry.target;
        const target = parseInt(el.dataset.count);
        const suffix = el.dataset.suffix || '';
        const duration = 1800;
        const start = performance.now();
        const animate = (now) => {
          const elapsed = now - start;
          const progress = Math.min(elapsed / duration, 1);
          const ease = 1 - Math.pow(1 - progress, 3);
          el.textContent = Math.floor(ease * target) + suffix;
          if (progress < 1) requestAnimationFrame(animate);
        };
        requestAnimationFrame(animate);
        counterObserver.unobserve(el);
      }
    });
  }, { threshold: 0.5 });
  counters.forEach(el => counterObserver.observe(el));

  /* ─── FORM VALIDATION ─── */
  const form = document.getElementById('registerForm');
  if (form) {
    const fields = {
      fullName: { el: form.querySelector('#fullName'), msg: form.querySelector('#fullNameErr'), validate: v => v.trim().length >= 2 },
      phone:    { el: form.querySelector('#phone'),    msg: form.querySelector('#phoneErr'),    validate: v => /^(0|\+84)[3-9]\d{8}$/.test(v.trim()) },
      email:    { el: form.querySelector('#email'),    msg: form.querySelector('#emailErr'),    validate: v => /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(v.trim()) },
    };

    Object.values(fields).forEach(({ el, msg, validate }) => {
      if (!el) return;
      el.addEventListener('blur', () => {
        const ok = validate(el.value);
        el.parentElement.classList.toggle('has-error', !ok);
        el.classList.toggle('error', !ok);
      });
      el.addEventListener('input', () => {
        if (el.classList.contains('error') && validate(el.value)) {
          el.parentElement.classList.remove('has-error');
          el.classList.remove('error');
        }
      });
    });

    form.addEventListener('submit', async (e) => {
      e.preventDefault();
      let valid = true;
      Object.values(fields).forEach(({ el, validate }) => {
        if (!el) return;
        const ok = validate(el.value);
        if (!ok) {
          el.parentElement.classList.add('has-error');
          el.classList.add('error');
          valid = false;
        }
      });
      if (!valid) return;

      const btn = form.querySelector('.form-submit');
      btn.classList.add('loading');
      btn.innerHTML = '<span>Đang xử lý...</span>';

      // Simulate API call
      await new Promise(r => setTimeout(r, 1600));

      btn.classList.remove('loading');
      btn.innerHTML = '✓ Đăng ký thành công!';
      btn.style.background = '#16a34a';
      form.querySelectorAll('input, select').forEach(el => el.value = '');
      setTimeout(() => {
        btn.innerHTML = 'Đăng ký học thử miễn phí →';
        btn.style.background = '';
      }, 4000);
    });
  }

  /* ─── SCROLL TO TOP ─── */
  const scrollTopBtn = document.getElementById('scrollTop');
  if (scrollTopBtn) {
    scrollTopBtn.addEventListener('click', () => window.scrollTo({ top: 0, behavior: 'smooth' }));
  }

  /* ─── ACTIVE NAV LINK ON SCROLL ─── */
  const sections = document.querySelectorAll('section[id]');
  const navLinks = document.querySelectorAll('.nav-menu a');
  const sectionObserver = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        navLinks.forEach(a => {
          a.classList.toggle('active', a.getAttribute('href') === '#' + entry.target.id);
        });
      }
    });
  }, { threshold: 0.4 });
  sections.forEach(s => sectionObserver.observe(s));

});
