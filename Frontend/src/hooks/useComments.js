import { useState, useEffect } from "react";

import {
    addComment,
    getCommentsByAnswerId
}
from "../services/commentService";

function useComments(answerId) {

    const [comments, setComments] =
        useState([]);

    const [commentText, setCommentText] =
        useState("");

    useEffect(() => {
        loadComments();
    }, [answerId]);

    const loadComments = async () => {

        try {

            const res =
                await getCommentsByAnswerId(
                    answerId
                );

            setComments(
                res.data
            );
        }
        catch (err) {

            console.log(
                "Error loading comments:",
                err
            );
        }
    };

    const handleAddComment =
        async () => {

        if (!commentText.trim()) {
            return;
        }

        try {

            await addComment({
                content:
                    commentText,

                answerId
            });

            setCommentText("");

            loadComments();
        }
        catch (err) {

            console.log(
                "Error adding comment:",
                err
            );
        }
    };

    return {

        comments,

        commentText,

        setCommentText,

        handleAddComment
    };
}

export default useComments;