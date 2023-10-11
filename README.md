# svelte-template

Install Tailwind CSS
```
npm i
npm install -D tailwindcss postcss autoprefixer daisyui@latest
npx tailwindcss init
```

postcss.config.js
```
export default {
  plugins: {
    tailwindcss: {},
    autoprefixer: {},
  },
}
```

app.css
```
@tailwind base;
@tailwind components;
@tailwind utilities;

```

svelte.config.js
```
import adapter from '@sveltejs/adapter-auto'; // later adapter-cloudflare
import { vitePreprocess } from '@sveltejs/kit/vite';

/** @type {import('@sveltejs/kit').Config} */
const config = {
  kit: {
    adapter: adapter({
      fallback: '200.html'
    })
  },
  preprocess: vitePreprocess()
};

export default config;
```

tailwind.config.js
```
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
```

vite.config.js
```
import { sveltekit } from '@sveltejs/kit/vite';
import { defineConfig } from 'vite';

export default defineConfig({
  plugins: [sveltekit()],
  build: {
    base: '/',
    assetsDir: 'static'
  }
});
```
