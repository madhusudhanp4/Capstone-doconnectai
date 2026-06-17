import Swal from "sweetalert2";

export const confirmDelete = () => {
    return Swal.fire({
        title: "Delete?",
        text: "This action cannot be undone.",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Delete",
        cancelButtonText: "Cancel",
        background: "#111827",
        color: "#fff",
        confirmButtonColor: "#ef4444",
        cancelButtonColor: "#6366f1"
    });
};

export const successAlert = (msg) => {
    Swal.fire({
        icon: "success",
        title: msg,
        timer: 1500,
        showConfirmButton: false,
        background: "#111827",
        color: "#fff"
    });
};

export const errorAlert = (msg) => {
    Swal.fire({
        icon: "error",
        title: msg,
        timer: 1500,
        showConfirmButton: false,
        background: "#111827",
        color: "#fff"
    });
};

export const validationAlert = (errors) => {

    const message = Object.values(errors)
        .map(err => `• ${err}`)
        .join("<br>");

    Swal.fire({
        icon: "error",
        title: "Validation Failed",
        html: message,
        confirmButtonColor: "#6366f1",
        background: "#111827",
        color: "#fff"
    });
};