(ns leiningen.deploy-uberjar
  "Build and deploy jar to remote repository."
  (:require [leiningen.uberjar :as uberjar]
  	        [leiningen.pom :as pom]
            [leiningen.deploy :as deploy]))

(defn files-for [project repo]
  (let [signed? (deploy/sign-for-repo? repo)
        ;; If pom is put in "target/", :auto-clean true will remove it if the
        ;; jar is created afterwards. So make jar first, then pom.
        artifacts {[:extension "jar"] (uberjar/uberjar project) [:extension "pom"] (pom/pom project)}
        sig-opts (deploy/signing-opts project repo)]
    (prn "HERE!!!!" artifacts)
    (if (and signed? (not (.endsWith (:version project) "-SNAPSHOT")))
      (reduce merge artifacts (map #(deploy/signature-for-artifact % sig-opts)
                                   artifacts))
      artifacts)))

(defn ^:no-project-needed deploy-uberjar
  ([project]
     (deploy-uberjar project (if (pom/snapshot? project)
                       "snapshots"
                       "releases")))
  ([project repository]
     (with-redefs [deploy/files-for files-for]
       (deploy/deploy project repository))))
