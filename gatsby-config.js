module.exports = {
  siteMetadata: {
    title: "Abhinav Sharma || Home",
    description:
      "My mission as a developer is to contribute to applications that empower and motivate lives. Let's change the world together.",
    siteUrl: "https://www.abhi18av.com",
    author: "abhi18av",
    twitter: "abhi18av"
  },
  plugins: [
    {
      resolve: `gatsby-plugin-manifest`,
      options: {
        name: `Abhinav Sharma`,
        short_name: `AbhinavSharma`,
        start_url: `/`,
        background_color: `#337ab7`,
        theme_color: `#337ab7`,
        display: `fullscreen`,
        icon: `src/static/logo/favicon.png` // This path is relative to the root of the site.
      }
    },
    {
      resolve: `gatsby-source-filesystem`,
      options: {
        name: `data`,
        path: `${__dirname}/src/data/`
      }
    },
    {
      resolve: `gatsby-source-filesystem`,
      options: {
        path: `${__dirname}/src/images`,
        name: `images`
      }
    },
    {
      resolve: `gatsby-plugin-google-analytics`,
      options: {
        trackingId: "UA-102315844-1",
        head: true
      }
    },
    `gatsby-plugin-sitemap`,
    `gatsby-transformer-sharp`,
    `gatsby-plugin-sharp`,
    `gatsby-plugin-react-helmet`
  ]
};
