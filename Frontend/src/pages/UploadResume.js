import React, { useState } from 'react';
import axios from 'axios';

const UploadResume = () => {
    const [file, setFile] = useState(null);
    const [userId, setUserId] = useState(""); // Example field for user ID
    const [message, setMessage] = useState("");

    const handleFileChange = (e) => {
        setFile(e.target.files[0]);
    };

    const handleUserIdChange = (e) => {
        setUserId(e.target.value);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const formData = new FormData();
        formData.append("userId", userId);
        formData.append("file", file);

        try {
            const response = await axios.post(`${process.env.REACT_APP_API_URL}/resume/upload`, formData, {
                headers: { "Content-Type": "multipart/form-data" },
            });
            setMessage(response.data);
        } catch (error) {
            setMessage("Failed to upload resume.");
            console.error("Error:", error);
        }
    };

    return (
        <div style={{ padding: "20px", maxWidth: "400px", margin: "auto" }}>
            <h2>Upload Resume</h2>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    placeholder="Enter User ID"
                    value={userId}
                    onChange={handleUserIdChange}
                    required
                    style={{ width: "100%", marginBottom: "10px", padding: "8px" }}
                />
                <input
                    type="file"
                    onChange={handleFileChange}
                    required
                    style={{ marginBottom: "10px" }}
                />
                <button type="submit" style={{ padding: "10px", width: "100%", background: "blue", color: "white", border: "none" }}>
                    Upload
                </button>
            </form>
            {message && <p>{message}</p>}
        </div>
    );
};

export default UploadResume;
