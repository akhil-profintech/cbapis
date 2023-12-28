import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ICREHighlights } from '../cre-highlights.model';
import { CREHighlightsService } from '../service/cre-highlights.service';

@Component({
  standalone: true,
  templateUrl: './cre-highlights-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class CREHighlightsDeleteDialogComponent {
  cREHighlights?: ICREHighlights;

  constructor(
    protected cREHighlightsService: CREHighlightsService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.cREHighlightsService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
