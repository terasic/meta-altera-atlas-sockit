/*
 * Copyright (C) 2015 Altera Corporation
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place, Suite 330, Boston, MA 02111-1307 USA
 */

#include "my_altera_msgdma_csr_regs.h"
#include "my_altera_msgdma_descriptor_regs.h"

/*
 * PERIPHBASE and Global Timer offset
 */
#define PERIPHBASE		(0xFFFEC000)
#define GLOBAL_TIMER_OFFSET	(0x200)

/*
 * msgdma driver values
 */
#define DMA_BUFFER_ALLOC_SIZE	(64 *1024)
#define DMA_ACP_OFFSET		(0x40000000)

#define DMA_DATA_OFFSET		(0 * 1024)
#define DMA_RESULT_OFFSET	(32 * 1024)
#define DMA_RESULT_SPAN		(32 * 1024)

#define DMA_DATA_RAM_OFFSET	(0x40000)
#define DMA_RAM_DATA_ADDR	(DMA_DATA_RAM_OFFSET + DMA_DATA_OFFSET)
#define DMA_RAM_RESULTS_ADDR	(DMA_DATA_RAM_OFFSET + DMA_RESULT_OFFSET)

#define CSR_STATUS_REG			(ALTERA_MSGDMA_CSR_STATUS_REG << 2)
#define CSR_CONTROL_REG			(ALTERA_MSGDMA_CSR_CONTROL_REG << 2)
#define CSR_DESCRIPTOR_FILL_LEVEL_REG	(ALTERA_MSGDMA_CSR_DESCRIPTOR_FILL_LEVEL_REG << 2)
#define CSR_RESPONSE_FILL_LEVEL_REG	(ALTERA_MSGDMA_CSR_RESPONSE_FILL_LEVEL_REG << 2)

#define DESC_READ_ADDRESS_REG		(ALTERA_MSGDMA_DESCRIPTOR_READ_ADDRESS_REG << 2)
#define DESC_WRITE_ADDRESS_REG		(ALTERA_MSGDMA_DESCRIPTOR_WRITE_ADDRESS_REG << 2)
#define DESC_LENGTH_REG			(ALTERA_MSGDMA_DESCRIPTOR_LENGTH_REG << 2)
#define DESC_CONTROL_REG		(ALTERA_MSGDMA_DESCRIPTOR_CONTROL_STANDARD_REG << 2)

#define START_DMA_FROM_FFT_MASK	( \
		ALTERA_MSGDMA_DESCRIPTOR_CONTROL_GO_MASK | \
		ALTERA_MSGDMA_DESCRIPTOR_CONTROL_END_ON_EOP_MASK)

#define START_DMA_TO_FFT_MASK	( \
		ALTERA_MSGDMA_DESCRIPTOR_CONTROL_GO_MASK | \
		ALTERA_MSGDMA_DESCRIPTOR_CONTROL_GENERATE_SOP_MASK |\
		ALTERA_MSGDMA_DESCRIPTOR_CONTROL_GENERATE_EOP_MASK)

