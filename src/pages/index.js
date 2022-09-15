import React from "react";
import clsx from "clsx";
import Layout from "@theme/Layout";
import Link from "@docusaurus/Link";
import useDocusaurusContext from "@docusaurus/useDocusaurusContext";
import useBaseUrl from "@docusaurus/useBaseUrl";
import indexStyles from "./index.module.css";
import { SocialIcon } from 'react-social-icons';
import AbhinavSharmaImageUrl from "@site/static/img/abhinav.jpeg";

export default function Home() {
  const context = useDocusaurusContext();
  const { siteConfig = {} } = context;
  return (
    <Layout
      title={`Home Page`}
      description="Abhinav Sharma (@abhi18av) personal website."
    >
      <main>


        <div class="hero">
          <div class="container">
            <div class="row row--no-gutters">
              <div class="col"> </div>

              <div class="col col--offset-4">
                <div class="col--offset">
                  <img src={AbhinavSharmaImageUrl} width={370} height={280} />
                </div>
                <p class="hero__subtitle">
                  <p class="leading-loose">
                    {'Abhinav Sharma ❤️ Science and Education'}
                  </p>
                </p>

                <div>
                  <span class="margin--sm">  <SocialIcon url="https://github.com/abhi18av" /></span>
                  <span class="margin--sm"> <SocialIcon url="https://www.linkedin.com/in/abhi18av" /> </span>
                  <span class="margin--sm"> <SocialIcon url="https://www.youtube.com/channel/UCcDGxGoHwvmmzS01HT7B1LQ?view_as=subscriber" /> </span>
                  <span class="margin--sm"> <SocialIcon url="https://www.facebook.com/abhi18av" /> </span>
                  <span class="margin--sm"> <SocialIcon url="https://twitter.com/abhi18av" /> </span>
                </div>

                <div />
                <div />
                <div />

                {/* <div class="margin-top--sm padding-top--sm"> */}
                {/*   {"BioSharp OÜ is a company registered in"} */}
                {/* </div> */}

              </div>

            </div>

          </div>
        </div>


      </main>
    </Layout >
  );
}
