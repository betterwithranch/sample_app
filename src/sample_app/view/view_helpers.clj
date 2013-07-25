(ns sample-app.view.view-helpers
  "Put helper functions for views in this namespace."
  (:use
    [joodo.views :only (render-partial *view-context*)]
    [chee.string :refer [gsub]]
    [chee.datetime :refer [parse-datetime]]
    [hiccup.page]
    [hiccup.form]
    [sample-app.controller.post-controller :refer [ blog-post-filenames]]
    [clojure.string :as string :refer [split]]))

  
  (defn post-parts [post-file-name]
    (string/split post-file-name #"(\.)|(_)"))

  (defn get-post-name [post-file-name]
    (gsub
       (second (post-parts post-file-name))
       #"-"
       (fn [_] " ")))

  (defn get-post-date [post-file-name]
    (parse-datetime :dense
                    (str (first (post-parts post-file-name)) "000000")))

  (defn order-posts [post-file-names]
    (sort-by
      #(Integer/parseInt (first (post-parts %)))
      >
      post-file-names))

  (defn get-post-route [post-file-name]
    (let [this-post-parts (post-parts post-file-name)
          date (first this-post-parts)
          title (second this-post-parts)]
      (str "/post/" date "_" title)))
