/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        // Primary colors
        'tenpo-primary': '#00BFA5',      // Main brand color (original tenpo-green)
        'tenpo-primary-dark': '#009D87', // Darker variant for hover states
        'tenpo-primary-light': '#7FDECB', // Lighter variant for backgrounds/accents

        // Secondary colors
        'tenpo-secondary': '#007AFF',    // Secondary brand color (original tenpo-blue)
        'tenpo-secondary-dark': '#0062CC', // Darker variant for hover states
        'tenpo-secondary-light': '#B8D8FF', // Lighter variant for backgrounds/accents

        // Accent colors (new additions)
        'tenpo-accent-1': '#5E35B1',     // Purple accent for special elements
        'tenpo-accent-2': '#F57C00',     // Orange accent for CTAs and highlights

        // Neutral colors (refined)
        'tenpo-neutral-900': '#212121',  // For highest contrast text (formerly tenpo-black)
        'tenpo-neutral-800': '#333333',  // For high contrast text (formerly tenpo-gray-dark)
        'tenpo-neutral-600': '#767676',  // For medium contrast text (formerly tenpo-gray)
        'tenpo-neutral-300': '#D1D1D1',  // For borders and dividers (new)
        'tenpo-neutral-200': '#EEEEEE',  // For input backgrounds (new)
        'tenpo-neutral-100': '#F5F5F5',  // For page backgrounds (formerly tenpo-gray-light)
        'tenpo-neutral-50': '#FFFFFF',   // For cards and overlays (formerly tenpo-white)

        // Feedback colors (refined)
        'tenpo-success': '#00BFA5',      // Success states (using primary green)
        'tenpo-warning': '#FFB300',      // Warning states (new)
        'tenpo-error': '#FF3B30',        // Error states (same as original tenpo-red)
        'tenpo-info': '#007AFF',         // Information states (using secondary blue)
      },
      fontFamily: {
        'sans': ['Inter', 'system-ui', 'sans-serif'],
        'inter': ['Inter', 'sans-serif'],
      },
      container: {
        center: true,
        padding: '1rem',
        screens: {
          lg: '1125px',
          xl: '1125px',
          '2xl': '1125px',
          '3xl': '1500px'
        },
      },
    },
  },
  plugins: [],
}