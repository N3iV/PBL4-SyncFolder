import React from "react";
import { Route, Switch } from "react-router-dom";
import { path } from "./constant/path";
import Home from "./pages/Home";
import Login from "./pages/Login";

const Routes = () => {
  return (
    <Switch>
      <Route path={path.home} exact>
        <Home />
      </Route>

      <Route path={path.login}>
        <Login />
      </Route>

      {/* <Route path={path.notFound}>
        <NotFound />
      </Route> */}
    </Switch>
  );
};

export default Routes;
