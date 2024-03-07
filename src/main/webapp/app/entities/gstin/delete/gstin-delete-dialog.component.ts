import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IGstin } from '../gstin.model';
import { GstinService } from '../service/gstin.service';

@Component({
  standalone: true,
  templateUrl: './gstin-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class GstinDeleteDialogComponent {
  gstin?: IGstin;

  constructor(
    protected gstinService: GstinService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.gstinService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
