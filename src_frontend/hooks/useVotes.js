import { useState, useEffect } from "react";

import {
    addVote,
    getVoteCount,
    getVotesByAnswerId
}
from "../services/voteService";

function useVotes(answerId) {

    const [score, setScore] =
        useState(0);

    const [upVotes, setUpVotes] =
        useState(0);

    const [downVotes, setDownVotes] =
        useState(0);

    useEffect(() => {
        loadVotes();
    }, [answerId]);

    const loadVotes = async () => {

        try {

            const scoreRes =
                await getVoteCount(
                    answerId
                );

            setScore(
                scoreRes.data
            );

            const votesRes =
                await getVotesByAnswerId(
                    answerId
                );

            const votes =
                votesRes.data;

            setUpVotes(
                votes.filter(
                    v =>
                        v.type ===
                        "UPVOTE"
                ).length
            );

            setDownVotes(
                votes.filter(
                    v =>
                        v.type ===
                        "DOWNVOTE"
                ).length
            );
        }
        catch (err) {

            console.log(
                "Error loading votes:",
                err
            );
        }
    };

    const handleUpVote =
        async () => {

        try {

            await addVote({
                type: "UPVOTE",
                answerId
            });

            loadVotes();
        }
        catch (err) {

            console.log(
                "Upvote failed:",
                err
            );
        }
    };

    const handleDownVote =
        async () => {

        try {

            await addVote({
                type: "DOWNVOTE",
                answerId
            });

            loadVotes();
        }
        catch (err) {

            console.log(
                "Downvote failed:",
                err
            );
        }
    };

    return {

        score,
        upVotes,
        downVotes,

        handleUpVote,
        handleDownVote
    };
}

export default useVotes;