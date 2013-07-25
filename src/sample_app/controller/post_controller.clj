(ns sample-app.controller.post-controller
  (:require [compojure.core :refer (GET context defroutes)]
            [joodo.views :refer (render-template)]))

(def ^{:private true} current-path
  (. (java.io.File. ".") getCanonicalPath))

(def ^:dynamic blog-post-directory
  (clojure.java.io/file (str current-path "/src/sample_app/view/posts")))

(defn blog-post-filenames []
  (map
    #(.getName %)
    (remove
      #(.isDirectory %)
      (file-seq blog-post-directory))))

(defn- blog-post-exists? [post-route]
  (some #(= % (str post-route ".hiccup.clj")) (blog-post-filenames)))

(defn- render-not-found [error-msg]
  {:status 404
   :headers {}
   :body (render-template "not_found" :error error-msg)})

(defn- render-post [post-route]
  (if (blog-post-exists? post-route)
    (render-template (str "posts/" post-route))
    (render-not-found "Blog post does not exist.")))

(defroutes post-controller
  (GET "/post" [] (render-not-found "You must specify a blog post."))
  (context "/post" []
           (GET "/:post-route" [post-route] (render-post post-route))))
