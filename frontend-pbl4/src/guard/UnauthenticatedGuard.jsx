import { Navigate, Outlet } from "react-router-dom";
import { path } from "../constant/path";
import { useAuthenticated } from "../hooks/useAuthenticated";
export default function UnauthenticatedGuard({ children }) {
  const authenticated = useAuthenticated();
  if (authenticated) {
    return <Navigate to={path.home} />;
  }
  return children ? children : <Outlet />;
}
