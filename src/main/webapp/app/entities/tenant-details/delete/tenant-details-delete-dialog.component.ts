import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ITenantDetails } from '../tenant-details.model';
import { TenantDetailsService } from '../service/tenant-details.service';

@Component({
  standalone: true,
  templateUrl: './tenant-details-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class TenantDetailsDeleteDialogComponent {
  tenantDetails?: ITenantDetails;

  constructor(
    protected tenantDetailsService: TenantDetailsService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tenantDetailsService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
