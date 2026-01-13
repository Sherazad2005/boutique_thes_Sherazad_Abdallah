document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll(".delete-link").forEach(link => {
        link.addEventListener("click", (e) => {
            if (!confirm("Confirmer la suppression de ce produit ?")) {
                e.preventDefault();
            }
        });
    });
});
