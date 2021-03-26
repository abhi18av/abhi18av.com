/** @type {import('@docusaurus/types').DocusaurusConfig} */
module.exports = {
  title: "Abhinav Sharma",
  tagline: "Abhinav Sharma's personal website.",
  url: "https://abhi18av.com",
  baseUrl: "/",
  onBrokenLinks: "throw",
  onBrokenMarkdownLinks: "warn",
  favicon: "img/abhinav.png",
  organizationName: "abhi18av",
  projectName: "abhi18av.com",
  themeConfig: {
    navbar: {
      // title: "Abhinav Sharma",
      logo: {
        alt: "Abhinav Sharma Home Page",
        src: "img/abhinav.png",
      },
      items: [
        // {
        //   to: "docs/",
        //   activeBasePath: "docs",
        //   label: "Docs",
        //   position: "left",
        // },
        { to: "blog", label: "Blog", position: "left" },
      ],
    },
    googleAnalytics: {
      trackingID: "TODO",
      anonymizeIP: false,
    },
    footer: {
      style: "dark",
      links: [],
      copyright: `Copyright © ${new Date().getFullYear()}, All rights reserved. Abhinav Sharma`,
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
            "https://github.com/facebook/docusaurus/edit/master/website/blog/",
          blogSidebarCount: "ALL",
          blogSidebarTitle: "Previous scribblings",
          postsPerPage: 10,
          feedOptions: {
            type: "all",
            title: `TODO`, // default to siteConfig.title
            description: `TODO`, // default to  `${siteConfig.title} Blog`
            copyright: `Copyright © ${new Date().getFullYear()}, All rights reserved. Abhinav Sharma`,
            language: undefined, // possible values: http://www.w3.org/TR/REC-html40/struct/dirlang.html#langcodes
          },
        },
        theme: {
          customCss: require.resolve("./src/css/custom.css"),
        },
      },
    ],
  ],
  plugins: [
    [
      "@docusaurus/plugin-sitemap",
      {
        changefreq: "weekly",
        priority: 0.5,
        trailingSlash: false,
      },
    ],
  ],
};
