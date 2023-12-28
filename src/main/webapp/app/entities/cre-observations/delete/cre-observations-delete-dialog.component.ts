import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ICREObservations } from '../cre-observations.model';
import { CREObservationsService } from '../service/cre-observations.service';

@Component({
  standalone: true,
  templateUrl: './cre-observations-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class CREObservationsDeleteDialogComponent {
  cREObservations?: ICREObservations;

  constructor(
    protected cREObservationsService: CREObservationsService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.cREObservationsService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
