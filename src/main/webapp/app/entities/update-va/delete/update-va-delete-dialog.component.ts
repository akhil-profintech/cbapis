import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IUpdateVA } from '../update-va.model';
import { UpdateVAService } from '../service/update-va.service';

@Component({
  standalone: true,
  templateUrl: './update-va-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class UpdateVADeleteDialogComponent {
  updateVA?: IUpdateVA;

  constructor(
    protected updateVAService: UpdateVAService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.updateVAService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
