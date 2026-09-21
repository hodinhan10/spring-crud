const userApi = {
  list(keyword = "") {
    return APIInstance.get("/users", { params: { keyword } });
  },
  detail(id) {
    return APIInstance.get(`/users/${id}`);
  },
  create(data) {
    return APIInstance.post("/users", data);
  },
  update(id, data) {
    return APIInstance.put(`/users/${id}`, data);
  },
  remove(id) {
    return APIInstance.delete(`/users/${id}`);
  },
};

const facultyApi = {
  list() {
    return APIInstance.get("/faculties");
  },
};

const studentApi = {
  list(keyword = "") {
    return APIInstance.get("/students", { params: { keyword } });
  },
  detail(id) {
    return APIInstance.get(`/students/${id}`);
  },
  create(facultyId, data) {
    return APIInstance.post(`/students?facultyId=${facultyId}`, data);
  },
  update(id, data) {
    return APIInstance.put(`/students/${id}`, data);
  },
  remove(id) {
    return APIInstance.delete(`/students/${id}`);
  },
};
