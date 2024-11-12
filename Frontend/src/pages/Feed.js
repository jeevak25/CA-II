import {
    Box,
    Card,
    Grid,
    TextField,
    Typography,
    InputAdornment,
    Button, typographyClasses,
} from "@mui/material";
import axios from "axios";
import React, { useEffect, useState } from "react";
import SearchIcon from "@mui/icons-material/Search";
import { Link } from "react-router-dom";
import {blue} from "@mui/material/colors";

const Feed = () => {
    const [query, setQuery] = useState("");
    const [post, setPost] = useState([]);
    const [isLoggedIn, setIsLoggedIn] = useState(
        () => localStorage.getItem("isLoggedIn") === "true"
    );

    useEffect(() => {
        const fetchPosts = async () => {
            const response = await axios.get(`http://localhost:8080/posts/${query}`);
            setPost(response.data);
        };
        const fetchInitialPosts = async () => {
            const response = await axios.get(`http://localhost:8080/allPosts`);
            console.log(response);
            setPost(response.data);
        };
        if (query.length === 0) fetchInitialPosts();
        if (query.length > 2) fetchPosts();
    }, [query]);

    console.log(post);

    return (

        <Grid container spacing={2} sx={{ margin: "2%" }}>
            <Grid item xs={12}>
                <Box sx={{ display: "flex", alignItems: "center" }}>
                    <Button sx={{ margin: "1% 2%" }} variant="outlined">
                        <Link to="/" style={{ color: "black" }}> Home</Link>
                    </Button>


                        <>
                            <Button sx={{ margin: "1% 2%" }} variant="outlined">
                                <Link to="/Create" style={{ color: "black" }}>
                                    Create
                                </Link>
                            </Button>
                            <Button sx={{ margin: "1% 2%" }} variant="outlined">
                                <Link to="/Dashboard" style={{ color: "black" }}>
                                    Dashboard
                                </Link>
                            </Button>
                            <Button sx={{ margin: "1% 2%" }} variant="outlined">
                                <Link to="/UploadResume" style={{ color: "black" }}>
                                    Resume Upload
                                </Link>
                            </Button>
                        </>

                </Box>

                <Typography variant="h3" sx={{ fontWeight: 600, marginBottom: "2%" , color: "#191970" }}>
                    Job Listings Feed
                </Typography>

                <Box>
                    <TextField
                        InputProps={{
                            startAdornment: (
                                <InputAdornment position="start">
                                    <SearchIcon />
                                </InputAdornment>
                            ),
                        }}
                        placeholder="Search..."
                        sx={{ width: "75%", padding: "2% auto" }}
                        fullWidth
                        onChange={(e) => setQuery(e.target.value)}
                    />
                </Box>
            </Grid>

            {post &&
                post.map((p) => {
                    return (
                        <Grid key={p.id} item xs={12} md={6} lg={4}>
                            <Card sx={{ padding: "3%", overflow: "hidden", width: "84%" }}>
                                <Typography
                                    variant="h5"
                                    sx={{ fontSize: "2rem", fontWeight: "600" }}
                                >
                                    {p.profile}
                                </Typography>
                                <Typography sx={{ color: "#585858", marginTop: "2%" }} variant="body">
                                    Description: {p.desc}
                                </Typography>
                                <br />
                                <Typography variant="h6">
                                    Years of Experience: {p.exp} years
                                </Typography>
                                <Typography gutterBottom variant="body">
                                    Skills:
                                </Typography>
                                {p.techs.map((s, i) => (
                                    <Typography variant="body" gutterBottom key={i}>
                                        {s} .
                                        {` `}
                                    </Typography>
                                ))}
                            </Card>
                        </Grid>
                    );
                })}
        </Grid>
    );
};

export default Feed;
