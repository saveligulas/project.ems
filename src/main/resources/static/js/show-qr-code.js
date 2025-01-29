async function showQRCode(bookingId) {
    try {
        const response = await fetch(`/api/bookings/${bookingId}/qr`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error('Failed to fetch booking details');
        }

        const data = await response.json();

        const container = document.getElementById('qr-code-container');
        container.innerHTML = '';

        new QRCode(container, {
            text: data.qrData,
            width: 256,
            height: 256
        });

        document.getElementById('modal-event-details').innerHTML = `
                    
                `;

        const modal = document.getElementById('qr-modal');
        modal.showModal();
    } catch (error) {
        console.error('Error generating QR code:', error);
        alert('Failed to generate QR code. Please try again.');
    }
}

function closeQRModal() {
    const modal = document.getElementById('qr-modal');
    modal.close();
}