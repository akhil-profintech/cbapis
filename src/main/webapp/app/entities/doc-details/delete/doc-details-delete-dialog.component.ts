import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IDocDetails } from '../doc-details.model';
import { DocDetailsService } from '../service/doc-details.service';

@Component({
  standalone: true,
  templateUrl: './doc-details-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class DocDetailsDeleteDialogComponent {
  docDetails?: IDocDetails;

  constructor(
    protected docDetailsService: DocDetailsService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.docDetailsService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
