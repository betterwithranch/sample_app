[:h1 "Posts"]
(for [current-post-filename (order-posts (blog-post-filenames))]
  (list
    [:a {:href (get-post-route current-post-filename)}
        (get-post-name current-post-filename)]
    [:span " - " (.toString (get-post-date current-post-filename))]
    [:br]))
