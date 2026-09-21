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
  list(facultyId, keyword = "") {
    return APIInstance.get(`/faculties/${facultyId}/students`, { params: { keyword } });
  },
  detail(facultyId, studentId) {
    return APIInstance.get(`/faculties/${facultyId}/students/${studentId}`);
  },
  create(facultyId, data) {
    return APIInstance.post(`/faculties/${facultyId}/students`, data);
  },
  update(facultyId, studentId, data) {
    return APIInstance.put(`/faculties/${facultyId}/students/${studentId}`, data);
  },
  remove(facultyId, studentId) {
    return APIInstance.delete(`/faculties/${facultyId}/students/${studentId}`);
  },
};
