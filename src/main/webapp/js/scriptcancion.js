'use strict'
/* Manejo del botón de eliminar */
document.querySelectorAll(".delete-btn").forEach(btn => {
    btn.addEventListener("click", function (event) {
        event.preventDefault();

        showToast("¿Deseas eliminar la canción?", () => {
            window.location.href = btn.href;
        });
    });
});

/* Manejo del botón de editar */
document.querySelectorAll(".edit-btn").forEach(btn => {
    btn.addEventListener("click", function (event) {
        event.preventDefault();

        showToast("¿Deseas editar la canción?", () => {
            window.location.href = btn.href;
        });
    });
});

// Función para mostrar el toast
function showToast(message, onConfirm) {
    const toast = document.getElementById("toast");
    const toastMessage = document.getElementById("toast-message");
    const toastYes = document.getElementById("toast-yes");
    const toastNo = document.getElementById("toast-no");


    toastMessage.textContent = message;

    toast.style.display = "block";

    toastYes.onclick = function () {
        toast.style.display = "none";
        if (onConfirm) onConfirm();
    };

    toastNo.onclick = function () {
        toast.style.display = "none";
    };
}
