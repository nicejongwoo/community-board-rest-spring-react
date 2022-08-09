import './App.css';
import {BrowserRouter} from "react-router-dom";
import AppRouter from "./router/AppRouter";

function App() {
    return (
        <BrowserRouter>
            <main id="main">
                <AppRouter />
            </main>
        </BrowserRouter>
    );
}

export default App;
