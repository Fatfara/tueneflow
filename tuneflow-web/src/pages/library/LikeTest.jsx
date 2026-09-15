import { useState } from "react";
import api from "../../services/api";

function LikeTest() {
    const [message, setMessage] = useState("");

    const likeSong = async () => {
        try {
            const response = await api.post("/likes/1");

            console.log("Like response:", response.data);

            setMessage("❤️ Song liked successfully!");
        } catch (error) {
            console.error("Like error:", error);

            setMessage(
                error.response?.data?.message ||
                "Failed to like song"
            );
        }
    };

    return (
        <div className="p-6 text-white">

            <h1 className="text-2xl font-bold mb-4">
                Like API Test
            </h1>

            <p className="text-gray-400 mb-5">
                Song: Updated Sample Song
            </p>

            <button
                onClick={likeSong}
                className="bg-white text-black px-5 py-3 rounded-full font-semibold hover:bg-gray-200"
            >
                ❤️ Like Song
            </button>

            {message && (
                <p className="mt-5 text-gray-300">
                    {message}
                </p>
            )}

        </div>
    );
}

export default LikeTest;
