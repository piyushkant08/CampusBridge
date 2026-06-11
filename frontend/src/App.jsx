import { useEffect, useState } from 'react';
import {
  addComment,
  createRequest,
  deleteRequest,
  getComments,
  getRequests,
  updateStatus
} from './api.js';

const categories = ['ACADEMICS', 'PROJECTS', 'PLACEMENT', 'HOSTEL', 'LOST_FOUND', 'GENERAL'];
const priorities = ['LOW', 'MEDIUM', 'HIGH'];
const statuses = ['OPEN', 'IN_PROGRESS', 'RESOLVED'];

const emptyForm = {
  studentName: '',
  title: '',
  description: '',
  category: 'ACADEMICS',
  priority: 'MEDIUM'
};

function App() {
  const [requests, setRequests] = useState([]);
  const [form, setForm] = useState(emptyForm);
  const [filters, setFilters] = useState({ status: '', category: '' });
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState('');
  const [selectedRequestId, setSelectedRequestId] = useState(null);
  const [comments, setComments] = useState([]);
  const [commentForm, setCommentForm] = useState({ authorName: '', message: '' });

  async function loadRequests() {
    try {
      setLoading(true);
      const data = await getRequests(filters);
      setRequests(data);
    } catch (error) {
      setMessage(error.message);
    } finally {
      setLoading(false);
    }
  }

  async function loadComments(requestId) {
    try {
      const data = await getComments(requestId);
      setComments(data);
      setSelectedRequestId(requestId);
    } catch (error) {
      setMessage(error.message);
    }
  }

  useEffect(() => {
    loadRequests();
  }, [filters.status, filters.category]);

  function handleFormChange(event) {
    const { name, value } = event.target;
    setForm({ ...form, [name]: value });
  }

  function handleFilterChange(event) {
    const { name, value } = event.target;
    setFilters({ ...filters, [name]: value });
  }

  async function handleSubmit(event) {
    event.preventDefault();

    try {
      await createRequest(form);
      setForm(emptyForm);
      setMessage('Request created successfully');
      await loadRequests();
    } catch (error) {
      setMessage(error.message);
    }
  }

  async function handleStatusChange(id, status) {
    try {
      await updateStatus(id, status);
      setMessage('Status updated');
      await loadRequests();
    } catch (error) {
      setMessage(error.message);
    }
  }

  async function handleDelete(id) {
    try {
      await deleteRequest(id);
      setMessage('Request deleted');
      if (selectedRequestId === id) {
        setSelectedRequestId(null);
        setComments([]);
      }
      await loadRequests();
    } catch (error) {
      setMessage(error.message);
    }
  }

  async function handleCommentSubmit(event) {
    event.preventDefault();

    if (!selectedRequestId) {
      setMessage('Select a request first');
      return;
    }

    try {
      await addComment(selectedRequestId, commentForm);
      setCommentForm({ authorName: '', message: '' });
      setMessage('Comment added');
      await loadComments(selectedRequestId);
      await loadRequests();
    } catch (error) {
      setMessage(error.message);
    }
  }

  const openCount = requests.filter((request) => request.status === 'OPEN').length;
  const resolvedCount = requests.filter((request) => request.status === 'RESOLVED').length;

  return (
    <div className="page">
      <header className="hero">
        <div>
          <p className="eyebrow">Student Problem Solver</p>
          <h1>CampusBridge</h1>
          <p>
            A simple platform where students can raise academic doubts, project help requests,
            lost-and-found posts, hostel issues, and placement questions.
          </p>
        </div>
        <div className="stats">
          <div>
            <strong>{requests.length}</strong>
            <span>Total Requests</span>
          </div>
          <div>
            <strong>{openCount}</strong>
            <span>Open</span>
          </div>
          <div>
            <strong>{resolvedCount}</strong>
            <span>Resolved</span>
          </div>
        </div>
      </header>

      {message && <div className="notice">{message}</div>}

      <main className="layout">
        <section className="panel">
          <h2>Create Student Request</h2>
          <form onSubmit={handleSubmit} className="form">
            <input
              name="studentName"
              placeholder="Student name"
              value={form.studentName}
              onChange={handleFormChange}
              required
            />
            <input
              name="title"
              placeholder="Short title"
              value={form.title}
              onChange={handleFormChange}
              required
            />
            <textarea
              name="description"
              placeholder="Explain the problem clearly"
              value={form.description}
              onChange={handleFormChange}
              required
            />
            <div className="form-row">
              <select name="category" value={form.category} onChange={handleFormChange}>
                {categories.map((category) => (
                  <option key={category} value={category}>{category}</option>
                ))}
              </select>
              <select name="priority" value={form.priority} onChange={handleFormChange}>
                {priorities.map((priority) => (
                  <option key={priority} value={priority}>{priority}</option>
                ))}
              </select>
            </div>
            <button type="submit">Create Request</button>
          </form>

          <div className="comment-box">
            <h2>Add Comment / Help</h2>
            <p className="muted">Select a request from the list, then add your response.</p>
            <form onSubmit={handleCommentSubmit} className="form">
              <input
                placeholder="Your name"
                value={commentForm.authorName}
                onChange={(event) => setCommentForm({ ...commentForm, authorName: event.target.value })}
                required
              />
              <textarea
                placeholder="Write a helpful reply"
                value={commentForm.message}
                onChange={(event) => setCommentForm({ ...commentForm, message: event.target.value })}
                required
              />
              <button type="submit">Add Comment</button>
            </form>
          </div>
        </section>

        <section className="panel wide">
          <div className="list-header">
            <h2>Student Requests</h2>
            <div className="filters">
              <select name="status" value={filters.status} onChange={handleFilterChange}>
                <option value="">All Status</option>
                {statuses.map((status) => (
                  <option key={status} value={status}>{status}</option>
                ))}
              </select>
              <select name="category" value={filters.category} onChange={handleFilterChange}>
                <option value="">All Categories</option>
                {categories.map((category) => (
                  <option key={category} value={category}>{category}</option>
                ))}
              </select>
            </div>
          </div>

          {loading ? (
            <p className="muted">Loading requests...</p>
          ) : (
            <div className="cards">
              {requests.map((request) => (
                <article key={request.id} className="card">
                  <div className="card-top">
                    <div>
                      <h3>{request.title}</h3>
                      <p className="muted">Raised by {request.studentName}</p>
                    </div>
                    <span className={`badge ${request.priority.toLowerCase()}`}>{request.priority}</span>
                  </div>
                  <p>{request.description}</p>
                  <div className="tags">
                    <span>{request.category}</span>
                    <span>{request.status}</span>
                  </div>
                  <div className="actions">
                    <select
                      value={request.status}
                      onChange={(event) => handleStatusChange(request.id, event.target.value)}
                    >
                      {statuses.map((status) => (
                        <option key={status} value={status}>{status}</option>
                      ))}
                    </select>
                    <button className="secondary" onClick={() => loadComments(request.id)}>View Comments</button>
                    <button className="danger" onClick={() => handleDelete(request.id)}>Delete</button>
                  </div>
                </article>
              ))}
            </div>
          )}

          {selectedRequestId && (
            <div className="comments">
              <h2>Comments for Request #{selectedRequestId}</h2>
              {comments.length === 0 ? (
                <p className="muted">No comments yet.</p>
              ) : (
                comments.map((comment) => (
                  <div key={comment.id} className="comment">
                    <strong>{comment.authorName}</strong>
                    <p>{comment.message}</p>
                  </div>
                ))
              )}
            </div>
          )}
        </section>
      </main>
    </div>
  );
}

export default App;
