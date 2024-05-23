import "./App.css";
import { ToastContainer } from "react-toastify";

import { Routes, Route } from "react-router-dom";
import NavbarHome from "./Components/NavbarHome";
import Login from "./Components/Login";
import Registration from "./Components/Registration";
import Admin from "./Pages/Admin";
import User from "./Pages/User";
import Home from "./Pages/Home";

import Booking from "./Components/Booking";
import NotFound from "./Components/NotFound";
import EditCar from "./Components/EditCar";

function App() {
  return (
    <div>
      <NavbarHome />

      <ToastContainer
        position="top-right"
        autoClose={3000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        theme="colored"
      />

      <Routes>
        <Route path="/login" Component={Login}></Route>

        <Route path="/register" Component={Registration}></Route>

        <Route path="/" Component={Home}></Route>

        <Route path="/admin" Component={Admin}></Route>

        <Route path="/user" Component={User}></Route>

        <Route path="/booking" Component={Booking}></Route>

        <Route path="/editCar" Component={EditCar}></Route>

        <Route path="/*" Component={NotFound}></Route>
      </Routes>
    </div>
  );
}

export default App;
