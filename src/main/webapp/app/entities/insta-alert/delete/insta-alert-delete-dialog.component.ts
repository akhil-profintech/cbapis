import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IInstaAlert } from '../insta-alert.model';
import { InstaAlertService } from '../service/insta-alert.service';

@Component({
  standalone: true,
  templateUrl: './insta-alert-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class InstaAlertDeleteDialogComponent {
  instaAlert?: IInstaAlert;

  constructor(
    protected instaAlertService: InstaAlertService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.instaAlertService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
