import { Component, Input } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { ICREHighlights } from '../cre-highlights.model';

@Component({
  standalone: true,
  selector: 'jhi-cre-highlights-detail',
  templateUrl: './cre-highlights-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class CREHighlightsDetailComponent {
  @Input() cREHighlights: ICREHighlights | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  previousState(): void {
    window.history.back();
  }
}
