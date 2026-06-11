const API_BASE_URL = 'http://localhost:8080/api/requests';

async function handleResponse(response) {
  if (!response.ok) {
    let errorMessage = 'Request failed';
    try {
      const errorData = await response.json();
      errorMessage = errorData.message || errorMessage;
    } catch (error) {
      errorMessage = 'Server error';
    }
    throw new Error(errorMessage);
  }

  if (response.status === 204) {
    return null;
  }

  return response.json();
}

export async function getRequests(filters) {
  const params = new URLSearchParams();

  if (filters.status) {
    params.append('status', filters.status);
  }

  if (filters.category) {
    params.append('category', filters.category);
  }

  const queryString = params.toString();
  const url = queryString ? `${API_BASE_URL}?${queryString}` : API_BASE_URL;
  const response = await fetch(url);
  return handleResponse(response);
}

export async function createRequest(data) {
  const response = await fetch(API_BASE_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  });
  return handleResponse(response);
}

export async function updateStatus(id, status) {
  const response = await fetch(`${API_BASE_URL}/${id}/status`, {
    method: 'PATCH',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({ status })
  });
  return handleResponse(response);
}

export async function deleteRequest(id) {
  const response = await fetch(`${API_BASE_URL}/${id}`, {
    method: 'DELETE'
  });
  return handleResponse(response);
}

export async function getComments(id) {
  const response = await fetch(`${API_BASE_URL}/${id}/comments`);
  return handleResponse(response);
}

export async function addComment(id, data) {
  const response = await fetch(`${API_BASE_URL}/${id}/comments`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  });
  return handleResponse(response);
}
