import SwiftUI

public struct StaggeredGrid: Layout {
    private var columns: Int
    private var spacing: Double

    public init(columns: Int = 2, spacing: Double = 8) {
        self.columns = columns
        self.spacing = spacing
    }

    public func sizeThatFits(proposal: ProposedViewSize, subviews: Subviews, cache: inout ()) -> CGSize {
        calculateSize(for: subviews, in: proposal)
    }

    public func placeSubviews(in bounds: CGRect, proposal: ProposedViewSize, subviews: Subviews, cache: inout ()) {
        calculateSize(for: subviews, in: proposal, placeInBounds: bounds)
    }

    @discardableResult
    private func calculateSize(for subviews: Subviews, in proposal: ProposedViewSize, placeInBounds bounds: CGRect? = nil) -> CGSize {
        guard let maxWidth = proposal.width else { return .zero }
        let itemWidth = (maxWidth - spacing * Double(columns - 1)) / Double(columns)

        var shortestColumnIndex: Int = 0
        var columnsHeights: [Double] = Array(repeating: bounds?.minY ?? 0, count: columns)

        subviews.forEach { subview in
            let proposal = ProposedViewSize(
                width: itemWidth,
                height: subview.sizeThatFits(.unspecified).height
            )

            if let bounds {
                let x = (itemWidth + spacing) * Double(shortestColumnIndex) + bounds.minX
                subview.place(
                    at: .init(x: x, y: columnsHeights[shortestColumnIndex]),
                    anchor: .topLeading,
                    proposal: proposal
                )
            }

            let height = subview.dimensions(in: proposal).height
            columnsHeights[shortestColumnIndex] += height + spacing
            shortestColumnIndex = columnsHeights.enumerated().min { $0.element < $1.element }?.offset ?? 0
        }

        guard let maxHeight = columnsHeights.max() else { return .zero }

        return CGSize(
            width: maxWidth,
            height: maxHeight - spacing
        )
    }
}
