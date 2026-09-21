const API = axios.create({
  baseURL: "/api",
  headers: {
    "Content-Type": "application/json",
  },
});


const userApi = {
  list(keyword = "") {
    return API.get("/users", { params: { keyword } });
  },
  detail(id) {
    return API.get(`/users/${id}`);
  },
  create(data) {
    return API.post("/users", data);
  },
  update(id, data) {
    return API.put(`/users/${id}`, data);
  },
  remove(id) {
    return API.delete(`/users/${id}`);
  },
};

const facultyApi = {
  list() {
    return API.get("/faculties");
  },
};

const studentApi = {
  list(facultyId, keyword = "") {
    return API.get(`/faculties/${facultyId}/students`, { params: { keyword } });
  },
  detail(facultyId, studentId) {
    return API.get(`/faculties/${facultyId}/students/${studentId}`);
  },
  create(facultyId, data) {
    return API.post(`/faculties/${facultyId}/students`, data);
  },
  update(facultyId, studentId, data) {
    return API.put(`/faculties/${facultyId}/students/${studentId}`, data);
  },
  remove(facultyId, studentId) {
    return API.delete(`/faculties/${facultyId}/students/${studentId}`);
  },
};
