import axios from "axios";

const backendHost = process.env.REACT_APP_BACKEND_HOST;
const backendPort = process.env.REACT_APP_BACKEND_PORT;

export default axios.create({
  baseURL: `http://${backendHost}:${backendPort}`,
});
