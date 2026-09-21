const facultyApi = {
  list() {
    return APIInstance.get("/faculties");
  },
};

const studentApi = {
  list(facultyId) {
    const query = facultyId ? `?facultyId=${facultyId}` : "";
    return APIInstance.get(`/students${query}`);
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
