const userApi = {
  list() {
    return APIInstance.get("/users");
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
