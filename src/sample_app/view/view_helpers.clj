(ns sample-app.view.view-helpers
  "Put helper functions for views in this namespace."
  (:use
    [joodo.views :only (render-partial *view-context*)]
    [chee.string :refer [gsub]]
    [hiccup.page]
    [hiccup.form]
    [sample-app.controller.post-controller :refer [ blog-post-filenames]]
    [clojure.string :as string :refer [split]]))

 (defn get-post-name [post-file-name]
  (gsub
       (second (string/split post-file-name #"(\.)|(_)"))
   #"-"
   (fn [_] " ")))


