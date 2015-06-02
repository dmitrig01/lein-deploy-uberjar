(ns leiningen.deploy-uberjar
  "Build and deploy jar to remote repository."
  (:require [leiningen.uberjar :as uberjar]
            [leiningen.jar :as jar]
            [leiningen.deploy :as deploy]))

(let [orig-jar jar/jar]
  (defn ^:no-project-needed deploy-uberjar [project repository]
    (with-redefs [jar/jar (fn [& args] (with-redefs [jar/jar orig-jar] {[:extension "jar"] (apply uberjar/uberjar args)}))]
      (deploy/deploy project repository))))
