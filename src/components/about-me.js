import React from "react";
import "./about-me.css";
import Img from "gatsby-image";
import Hobbies from "./hobbies";

const AboutMe = ({ profileImg, hobbyImgs }) => {
  return (
    <section id="about" className="section">
      <h2 className="text-center">ABOUT ME</h2>
      <div className="section-content">
        <div className="row">
          <div className="col-12 col-sm-10 offset-sm-1 col-md-6 offset-md-0 col-lg-5 offset-lg-1 ">
            <Img
              title="Profile image"
              alt="image of Abhinav"
              sizes={profileImg.sizes}
              className="img-responsive center-block"
            />
          </div>
          <div className="col-12 col-sm-12 col-md-6">
            <h4>What I love doing</h4>
            <p>
              My passion is to contribute to products which
              empower and improve people's lives. I believe that through code we can make
              the world a better place by building products that empower
              creativity and innovation.
            </p>
            <h4> Engineering Products <i className="fa fa-heart" aria-hidden="true" /> Impact</h4>
            <p>
              Having worked in a couple startups and <i> accidentally </i> moving in the direction of managerial roles.
              I took a step away from that aspect to focus more intensively on becoming world-class with my engineering skills.
              Of course, this is a tough goal but I believe that with better and simpler tools it's definitely possible and I have
              been contributing to the open source projects in Clojure, Go, Python and OCaml.

    </p>
    <ul>
                I add value to the engineering teams in the following domains
                <li> Web Development</li>
                <li> Data Science</li>
                <li> DevOps & SysAdmin</li>
              </ul>
            <p>
              In my free time, I attend meetups, listen to engineering podcasts,
              read and tech blogs, and take other online courses in order to help me
              develop a growth mindset and become a better developer.

    </p>
    <ul>
                I also host a few YouTube channels such as
                <li> One Step Clojure </li>
                <li> The Data Wizard </li>
                <li> Bio Dragon </li>
              </ul>

            <Hobbies icons={hobbyImgs} />
          </div>
        </div>
        <div className="row">
          <div className=" col-12 col-md-4 col-lg-4 offset-lg-1">
            <div className="box">
              <h5>Toolbox</h5>
              <p>
                <b>Programming Languages:</b> Clojure(JVM), ClojureScript, Python, Go, OCaml
                <br />
                <b>FrontEnd Framework:</b> ReactJS, Fulcro, Re-frame, React Native
                <br />
                <b>BackEnd Framework:</b> Duct
                <br />
                <b>IDE:</b> VS Code, Spacemacs, IntelliJ IDEs
                <br />
                <b>VCS:</b> Git
                <br />
                <b>Cloud Computing:</b> AWS, Digital Ocean, Google Cloud
                <br />
                <b>Machine Learning:</b>  Tensorflow, StatsModel, Scikit-learn
                <br />
                <b>Design Frameworks:</b> Material Design, Semantic-UI
                <br />
                <b>PMS:</b> Basecamp, JIRA, YouTrack
                <br />
                <b>Databases: </b> SQLite3, PostgreSQL, MongoDB, Crux, Datomic
                <br />
              </p>
            </div>
          </div>
          <div className="col-lg-3 col-md-4 col-6 ">
            <div className="box">
              <h5>Learning</h5>
              <p>
                Currently: Fulcro, Pathom and Crux
              <br/>
                On Horizon: MirageOS Unikernels
              </p>
            </div>
          </div>
          <div className="col-lg-4  col-md-4 col-6 ">
            <div className="box">
              <h5>Hacking on</h5>
              <p>
                Contributing fulltime to Fulcro framework for React SPA in ClojureScript
              </p>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default AboutMe;
