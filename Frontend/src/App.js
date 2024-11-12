import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Home, Feed, Dashboard, Create} from "./pages";
import Signup from "./components/Signup";
import Login from "./components/Login";
import UploadResume from './pages/UploadResume';



function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Home />} />

                {/* Authentication Routes */}
                <Route path="/login" element={<Login />} />
                <Route path="/signup" element={<Signup />} />

                {/* Employee Routes */}
                <Route path="/employee/feed" element={<Feed />} />

                {/* Create and Dashboard Routes */}
                <Route path="/Create" element={<Create />} />
                <Route path="/Dashboard" element={<Dashboard />} />
            </Routes>

                <Routes>
                    <Route path="/UploadResume" element={<UploadResume />} />
                </Routes>

        </BrowserRouter>
    );
}

export default App;
