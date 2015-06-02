(defproject deploy-uberjar "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :eval-in-leiningen true
  :repositories [["snapshots" {:url "http://admin.branch.io:8081/nexus/content/repositories/snapshots"
                               :sign-releases false
                               :username "dev"
                               :password "kindred2015"}]
                 ["releases" {:url "http://admin.branch.io:8081/nexus/content/repositories/releases"
                              :sign-releases false
                              :username "dev"
                              :password "kindred2015"}]])
