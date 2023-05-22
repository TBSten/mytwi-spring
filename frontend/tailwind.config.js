/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ], theme: {
    extend: {
      colors: {
        primary: {
          100: "rgb(96,165,250)",
          200: "rgb(37,99,235)",
        },
        base: {
          100: "rgb(156,163,175)",
          200: "rgb(55,65,81)",
        },
      },
    },
  },
  plugins: [],
}

