// tailwind-plugins/space.js
const plugin = require('tailwindcss/plugin');

module.exports = plugin(function({ addUtilities, e, theme }) {
    const utilities = {};
    const spacing = theme('spacing');

    // space-y 类
    Object.entries(spacing).forEach(([key, value]) => {
        utilities[`.${e(`space-y-${key}`)}`] = {
            '--tw-space-y-reverse': '0',
            '& > * + *': {
                'margin-top': `calc(${value} * calc(1 - var(--tw-space-y-reverse)))`,
                'margin-bottom': `calc(${value} * var(--tw-space-y-reverse))`
            },
            '& > view + view': {
                'margin-top': `calc(${value} * calc(1 - var(--tw-space-y-reverse)))`,
                'margin-bottom': `calc(${value} * var(--tw-space-y-reverse))`
            },
            '& > uni-view + uni-view': {
                'margin-top': `calc(${value} * calc(1 - var(--tw-space-y-reverse)))`,
                'margin-bottom': `calc(${value} * var(--tw-space-y-reverse))`
            },
            '& > div + div': {
                'margin-top': `calc(${value} * calc(1 - var(--tw-space-y-reverse)))`,
                'margin-bottom': `calc(${value} * var(--tw-space-y-reverse))`
            }
        };
    });

    // space-x 类
    Object.entries(spacing).forEach(([key, value]) => {
        utilities[`.${e(`space-x-${key}`)}`] = {
            '--tw-space-x-reverse': '0',
            '& > * + *': {
                'margin-right': `calc(${value} * var(--tw-space-x-reverse))`,
                'margin-left': `calc(${value} * calc(1 - var(--tw-space-x-reverse)))`
            },
            '& > view + view': {
                'margin-right': `calc(${value} * var(--tw-space-x-reverse))`,
                'margin-left': `calc(${value} * calc(1 - var(--tw-space-x-reverse)))`
            },
            '& > uni-view + uni-view': {
                'margin-right': `calc(${value} * var(--tw-space-x-reverse))`,
                'margin-left': `calc(${value} * calc(1 - var(--tw-space-x-reverse)))`
            },
            '& > div + div': {
                'margin-right': `calc(${value} * var(--tw-space-x-reverse))`,
                'margin-left': `calc(${value} * calc(1 - var(--tw-space-x-reverse)))`
            }
        };
    });

    // reverse 类
    utilities[`.space-y-reverse`] = {
        '--tw-space-y-reverse': '1'
    };

    utilities[`.space-x-reverse`] = {
        '--tw-space-x-reverse': '1'
    };

    addUtilities(utilities);
});