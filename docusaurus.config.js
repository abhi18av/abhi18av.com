/** @type {import('@docusaurus/types').DocusaurusConfig} */
module.exports = {
  title: "Abhinav Sharma",
  tagline: "Abhinav Sharma's personal website.",
  url: "https://abhi18av.com",
  baseUrl: "/",
  onBrokenLinks: "throw",
  onBrokenMarkdownLinks: "warn",
  favicon: "img/abhinav.jpeg",
  organizationName: "abhi18av",
  projectName: "abhi18av.com",
  plugins: [
    async function mermaidPlugin() {
      const mdxMermaid = await import('mdx-mermaid')
      return {
        presets: [
          [
            'classic',
            {
              docs: {
                remarkPlugins: [
                  mdxMermaid.default
                ],
              }
            }
          ]
        ]
      }
    }
  ],
  themeConfig: {
    prism: {
      additionalLanguages: ['powershell', 'r', 'groovy', 'clojure', 'lua'],
      // theme: require('prism-react-renderer/themes/dracula'),
    },
    colorMode: {
      disableSwitch: true
    },

    navbar: {
      // title: "Abhinav Sharma",
      logo: {
        alt: "Abhinav Sharma Home Page",
        src: "img/abhinav.jpeg",
      },
      items: [
        // {
        //   to: "docs/",
        //   activeBasePath: "docs",
        //   label: "Docs",
        //   position: "left",
        // },
        { to: "curriculum_vitae", label: "Curriculum Vitae", position: "left" },
        { to: "blog", label: "Blog", position: "left" },
      ],
    },
    footer: {
      style: "dark",
      links: [],
      copyright: `Copyright © ${new Date().getFullYear()}, All rights reserved. Abhinav Sharma (@abhi18av)`,
    },
  },
  presets: [
    [
      "@docusaurus/preset-classic",
      {
        // docs: {
        //   sidebarPath: require.resolve('./sidebars.js'),
        //   // Please change this to your repo.
        //   editUrl:
        //     'https://github.com/facebook/docusaurus/edit/master/website/',
        // },
        blog: {
          showReadingTime: true,
          editUrl:
            "https://github.com/abhi18av/abhi18av.com/edit/master/",
          blogSidebarCount: "ALL",
          blogSidebarTitle: "Blog Posts",
          postsPerPage: 10,
          feedOptions: {
            type: "all",
            // title: `TODO`, // default to siteConfig.title
            // description: `TODO`, // default to  `${siteConfig.title} Blog`
            copyright: `Copyright © ${new Date().getFullYear()}, All rights reserved. Abhinav Sharma`,
            language: "en-US", // possible values: http://www.w3.org/TR/REC-html40/struct/dirlang.html#langcodes
          },
        },
        theme: {
          customCss: require.resolve("./src/css/custom.css"),
        },
        // googleAnalytics: {
        //   trackingID: "G-TQ8GL2QYD4eTODO",
        //   anonymizeIP: false,
        // }
      },
    ],
  ],
};
