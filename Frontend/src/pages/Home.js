import React from "react";
import { Typography, Button } from "@mui/material";
import { Link } from "react-router-dom";
import "../App.css";

const Home = () => {
  return (
      <div>
        <Typography sx={{ margin: "5%" }} variant="h3" align="center">
          JIVAK WANKHEDE
          B436
          CA II CLOUD COMPUTING
        </Typography>
        <Typography sx={{ margin: "5%" }} variant="h3" align="center">
          Job Listing
        </Typography>
        <div>
          <ul className="ul">
            <li>
              <Button sx={{ margin: "2% 3%" }} variant="outlined">
                <Link to="/login" className="link">
                  Login
                </Link>
              </Button>
            </li>
            <li>
              <Button sx={{ margin: "2% 3%" }} variant="outlined">
                <Link to="/signup" className="link">
                  Signup
                </Link>
              </Button>
            </li>
          </ul>
        </div>
      </div>


  );


};

console.log(`Here : ${process.env.REACT_APP_API_URL}`); // Should log http://localhost:8080 if .env is set up correctly
export default Home;
