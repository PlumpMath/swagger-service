(ns swagger-service.routes.services
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer :all]
            [schema.core :as s]
            [clj-http.client :as client]
            [clojure.java.io :as io]
            [clojure.xml :as xml]))


(defn get-first-child [tag xml-node]
  (->> xml-node :content (filter #(= (:tag %) tag)) first))

<<<<<<< HEAD
=======
(defn parse-link [link]
  (->> link (get-first-child :url) :content first))

(defn parse-links [links]
  (->> links
       (get-first-child :data)
       (get-first-child :images)
       :content
       (map parse-link)))

>>>>>>> origin/master
(defn parse-xml [xml]
  (-> xml .getBytes io/input-stream xml/parse))

(defn get-links [link-count]
  (-> "http://thecatapi.com/api/images/get?format=xml&results_per_page=" 
      (str link-count)
      client/get
      :body
<<<<<<< HEAD
      parse-xml))

;(i/inspect-tree (get-links 1))
=======
      parse-xml
      parse-links))
>>>>>>> origin/master

;(i/inspect-tree (get-links 1))

<<<<<<< HEAD
;(i/inspect-tree (get-links 3))
  
=======
;(require '[clojure.inspector :as i])

(defapi service-routes
  {:swagger
   {:ui "/swagger-ui"
    :spec "/swagger.json"
    :data {:info {:title "cat link api"
                  :version "1.0.0"
                  :description "cats api"}
           :tags [{:name "thecatpi", :description "cat's api"}]}}}
 (context "/api" []
         :tags ["thecatpi"]
         (GET "/cat-links" []
              :query-params [link-count :- Long]
              :summary "returns a collection of image links"
              :return [s/Str]
              (ok (get-links link-count)))))
>>>>>>> origin/master
