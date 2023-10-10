/** @type {import('tailwindcss').Config} */
export default {
  content: ['./src/**/*.{html,js,svelte}'],
  theme: {
    extend: {
      colors: {
        // 'example': '#123456',
      }
    }
  },
  plugins: [require("daisyui")],
  daisyui: {
    themes: ["light"],
  },
};