import { RouterProvider, createBrowserRouter } from "react-router-dom";
import TopPage from "./routes/top";
import LoginPage from "./routes/login";
import SignUpPage from "./routes/signup";
import TweetDetailPage from "./routes/tweet/detail";

const router = createBrowserRouter([
  {
    path: "/",
    element: <TopPage />,
  },
  {
    path: "/login",
    element: <LoginPage />,
  },
  {
    path: "/signup",
    element: <SignUpPage />,
  },
  {
    path: "/tweet",
    children: [{ path: ":id", element: <TweetDetailPage /> }],
  },
]);

function App() {
  return (
    <div>
      <RouterProvider router={router} />
    </div>
  );
}

export default App;
