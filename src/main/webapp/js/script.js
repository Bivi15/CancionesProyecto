'use strict'
const loginForm = document.querySelector('.login-form'),
  signupForm = document.querySelector('.registro-form'),
  backLayer = document.querySelector('.back-layer');
  
  document.querySelector('.login button').addEventListener('pointerdown', () => {
      signupForm.classList.remove('active');
      loginForm.classList.add('active');
      backLayer.style.clipPath = '';
  });
  document.querySelector('.registro button').addEventListener('pointerdown', () => {
      loginForm.classList.remove('active');
      signupForm.classList.add('active');
      backLayer.style.clipPath = 'inset(0 0 0 50%)';
  });





