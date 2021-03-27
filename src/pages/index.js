import React from "react";
import clsx from "clsx";
import Layout from "@theme/Layout";
import Link from "@docusaurus/Link";
import useDocusaurusContext from "@docusaurus/useDocusaurusContext";
import useBaseUrl from "@docusaurus/useBaseUrl";
import styles from "./styles.module.css";
import AbhinavSharmaImageUrl from "@site/static/img/abhinav.png";

export default function Home() {
  const context = useDocusaurusContext();
  const { siteConfig = {} } = context;
  return (
    <Layout
      title={`Home Page`}
      description="Abhinav Sharma (@abhi18av) personal website."
    >
      <main>
        <div class="hero shadow--lw">
          <div class="container">
            <div class="row row--no-gutters">
              <div class="col "></div>
              <img src={AbhinavSharmaImageUrl} width={260} height={260} />
              <div class="col col--5">
                <h1 class="hero__title">Abhinav Sharma</h1>
                <p class="hero__subtitle"></p>
                <a
                  class="button button--outline button--secondary"
                  href="https://github.com/abhi18av"
                >
                  Github
                </a>

                <a
                  class="button button--outline button--secondary"
                  href="https://www.linkedin.com/in/abhi18av"
                >
                  LinkedIn
                </a>

                <a
                  class="button button--outline button--secondary"
                  href="https://www.youtube.com/channel/UCcDGxGoHwvmmzS01HT7B1LQ?view_as=subscriber"
                >
                  YouTube
                </a>

                <a
                  class="button button--outline button--secondary"
                  href="https://twitter.com/abhi18av"
                >
                  Twitter
                </a>
              </div>
              <div class="col"></div>
            </div>
          </div>
        </div>
      </main>
    </Layout>
  );
}
